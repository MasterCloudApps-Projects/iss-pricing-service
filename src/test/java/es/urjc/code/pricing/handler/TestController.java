package es.urjc.code.pricing.handler;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.mvel2.MVEL;
import org.mvel2.ParserConfiguration;
import org.mvel2.ParserContext;
import org.mvel2.compiler.ExecutableStatement;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.code.pricing.exception.BusinessException;
import es.urjc.code.pricing.exception.EntityNotFoundException;

@RestController
@Validated
public class TestController {

	@GetMapping("/test/not-found")
	public void entityNotFound() {
		throw new EntityNotFoundException("entity not found");
	}

	@GetMapping("/test/not-found/iae-with-message")
	public void entityNotFoundNonUniqueResultWithMessage() {
		throw new EntityNotFoundException("entity not found", new IllegalArgumentException());
	}

	@GetMapping("/test/not-found/iae")
	public void entityNotFoundNonUniqueResult() {
		throw new EntityNotFoundException(new IllegalArgumentException("illegal argument"));
	}

	@GetMapping("/test/business-exception")
	public void business() {
		throw new BusinessException("business error");
	}

	@GetMapping("/test/io-exception")
	public void io() throws IOException {
		throw new IOException("io error");
	}

	@GetMapping("/test/property-access-exception")
	public void propertyAccessException() {
		String str = "map.key";

		ParserConfiguration pconf = new ParserConfiguration();
		ParserContext pctx = new ParserContext(pconf);
		pctx.setStrongTyping(true);
		pctx.addInput("this", POJO.class);
		ExecutableStatement stmt = (ExecutableStatement) MVEL.compileExpression(str, pctx);

		POJO ctx = new POJO();
		MVEL.executeExpression(stmt, ctx);
	}

	@PostMapping("/test/validation-exception")
	public void validationError(@Valid @RequestBody TestFieldValidation dummy) {

	}

	public class POJO {

	}

	public static class TestFieldValidation {

		@NotNull
		private String dummy;

		public TestFieldValidation() {

		}

		public String getDummy() {
			return dummy;
		}

		public void setDummy(String dummy) {
			this.dummy = dummy;
		}
	}

}
