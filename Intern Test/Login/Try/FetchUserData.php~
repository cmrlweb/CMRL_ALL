<?PHP
	$con = mysqli_connect("localhost","android","anshul","android");
	/* check connection */
	if (mysqli_connect_errno()) {
    printf("Connect failed: %s\n", mysqli_connect_error());
    exit();
	}


	$myusername = $_POST["username"];
	$mypassword = $_POST["password"];

	/* create a prepared statement */
	if ($statement = mysqli_prepare($con, "SELECT name,age,username,password FROM User WHERE username = ? AND password = ?")) {

		  /* bind parameters for markers */
		  mysqli_stmt_bind_param($statement, "ss", $myusername, $mypassword);

		  /* execute query */
		  mysqli_stmt_execute($statement);

		  /* bind result variables */
		  mysqli_stmt_bind_result($statement, $name, $age, $username, $password);

		  /* fetch value */
		  mysqli_stmt_fetch($statement);

		  /* printf("%s age is %s\n", $username, $age); */

		  /* close statement */
		  mysqli_stmt_close($statement);
	}

	/* close connection */
	mysqli_close($con);

	$user = array();
	
	$user[name] = $name;
	$user[age] = $age;
	$user[username] = $username;
	$user[password] = $password;

	echo json_encode($user);


?>

