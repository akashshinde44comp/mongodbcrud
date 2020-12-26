package com.electionique.pojo;

import org.junit.Test;

import com.openpojo.reflection.filters.FilterPackageInfo;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

public class PojoTest {
	  // The package to test
	  private static final String POJO_PACKAGE = "com.electionique.entity";

	  @Test
	  public void testPojoStructureAndBehavior() {
	    Validator validator = ValidatorBuilder.create()
	                            .with(new SetterTester())
	                            .with(new GetterTester())
	                            .build();

	    validator.validate(POJO_PACKAGE, new FilterPackageInfo());
	  }
	}