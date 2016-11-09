# SalesTaxes

README


The software was tested with:
 - Java 1.8
 - Eclipse Neon
 - Maven 3
 
Informations
- Once imported the projects you can execute the only main method inside com.lastminute.salestaxes.Main to see the three output of the three shopping basket of the case study
- Yuo can also run the ReceiptTest unit test case in order to check if the total amount and the total sales taxes of the three shopping basket are correct


Java project explanation
The SalesTaxes project has the following structure:

SalesTaxes
	
	src 
		com.lastminute.salestaxes
							Main.java : main file that allows to launch the software and that generates the output for the three shopping basket of the test case
							ecommerce
								     Classes releated to shopping basket and receipt
							goods
									 Classes that describes all kinds of goods (releted to case study)
							policies
									 Classes that implements the sales taxes policies: It was adopted Strategy Design pattern (slightly edited) in order to calculate the sales taxes for different kinds of goods.
							resources
									BundleManager.java: A Singleton class that manages the properties files inside the same package
							
	test (unit tests)
		com.lastminute.salestaxes
							ecommerce
								     Test cases of shopping basket and receipt
							goods
									 Test cases of items classes
							policies
									 Test cases of policies classes
							resources
									 Test cases of BundleManager class
							
							AllTests.java: Test suite that allows to launch all test cases together

		

Notes

	For the amounts and its calculation was used java.math.BigDecimal that is the type designed by java architects to best represents money amounts and bacause it gives its user complete control over rounding behavior.
	The classes was designed inspired to the DRY principle (https://en.wikipedia.org/wiki/Don%27t_repeat_yourself) in order to make the code
	easy to understand, easy to manage and in case extend and less error prone.
