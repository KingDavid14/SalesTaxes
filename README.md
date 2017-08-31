# SalesTaxes

The software was developed and tested with:
 - Java 1.8
 - JUnit
 - Eclipse Neon
 - Maven 3
 - Git
 - Travis CI
 
Project structure
The SalesTaxes project has the following structure:

SalesTaxes
	
	src 
		com.ve.salestaxes
							Main.java : main file that allows to launch the software and that generates the output for the three shopping basket of the test case
							bo
								     	Business object classes releated (eg. basket, receipt, goods)
							resources
									BundleManager.java: A Singleton class that manages the properties files
							services
									Classes that implements the business logic
							
	resources
		Properties files
	test (unit tests)
		com.ve.salestaxes
							bo
								     Test cases of business object classes
							resources
									 Test cases of BundleManager class
							services
									 Test cases for service layer classes
							
							AllTests.java: Test suite that allows to launch all test cases together from the IDE

		

Notes

	For the amounts and its calculation was used java.math.BigDecimal that is the type designed by java architects to best represents money amounts and bacause it implements the complete control over rounding behavior.
	The classes was designed inspired to the DRY principle (https://en.wikipedia.org/wiki/Don%27t_repeat_yourself) in order to make the code
	easy to understand and easy to manage.

	
	To check the Travis CI build history see the following link https://travis-ci.org/KingDavid14/SalesTaxes
