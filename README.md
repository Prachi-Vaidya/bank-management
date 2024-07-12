# bank-management
Created a simple bank management system using Java, jdbc, hibernate and PostgreSQl
1. I have created a POJO class "BankPojoClass.java" for private attributes and Constructor, getters, setters and toString method are also there.
2. Inside a controlller class "BankControllerClass.java" i have created methods and by using SQL queries and Postgresql i have created the following functions:
	i. Create customer (customer name, branch, account type, balance).
	ii. Check balance (account no). {Print account number, name and balance.}
	iii. Withdrawal money (account no, amount). {Deduct amount from balance}
	iv. Find customer by type (account type). {Print all customer of given type.}
	v. Deposit money (account no, amount). {Add amount in balance}
	vi. Change branch (customer name). {Change branch of given customer and print.}
	vii. Print all customers (). {Print all table data.}
3. Additionally, i have created two more functions:
	1.Check user (customer name, account type) {Checks if the account already exsists in the databas}
	2.transfer (account no, account balance, customer name) {takes input from user to transfer money from one account to another}
4. For the convinience of the user, these functions are called in the service class  "BankServiceClass.java", in a menu format.

Steps to run the program:
1) Ensure PostgreSQL is installed: Make sure you have PostgreSQL installed and running on your machine.
2) Set up the database: Create a database named tadb in your PostgreSQL server.
Make sure the PostgreSQL server is running on localhost at port 5432.
The username and password for the database should be postgres.
3) Set up JDBC driver: Include the PostgreSQL JDBC driver in your project.
You can download it from the official PostgreSQL JDBC website (https://jdbc.postgresql.org).
4)Compile the Java code: Compile your BankServiceClass.java file using the Java compiler: javac BankServiceClass.java.
5) Run the program:
After running the program, the following steps are to be followed:
	1. The function createtable() will be called only once.
	2. As the program starts execution the connection with the database will be established.
	3. The menu will be provided for the user:
		"Choose operation:"
		"1. Create Customer"
		"2. Check Balance"
		"3. Withdraw Money"
		"4. Find customer by its account type"
		"5. Deposit Money"
		"6. Change Branch"
		"7. Print All Customers"
		"8. check if account already exsist:"
		"9. transfer money"
	4. when you chose option from the menu, the control will be tranfered into the respective 'case' in the switch case. Enter the input: Customer name, balance, branch, account type, and in some cases account number, according to the instructions.
	5. After the user input the desired input, the functionality will be executed.
	6. The user will be asked if they want to continue; if yes, the switch case will be executed again, and the same process will be executed again, till the user exsists the program.
