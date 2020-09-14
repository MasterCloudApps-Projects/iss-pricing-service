package es.urjc.code.pricing.application.dto;

import java.math.BigDecimal;

public class NumericQuestionAnswer extends QuestionAnswer<BigDecimal> {
    public NumericQuestionAnswer(String questionCode, BigDecimal answer) {
        super(questionCode, answer);
    }
}

