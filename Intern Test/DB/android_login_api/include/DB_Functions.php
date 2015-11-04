<?php

/**
 * @author Ravi Tamada
 * @link http://www.androidhive.info/2012/01/android-login-and-registration-with-php-mysql-and-sqlite/ Complete tutorial
 */

class DB_Functions {

    private $conn;

    // constructor
    function __construct() {
        require_once 'DB_Connect.php';
        // connecting to database
        $db = new Db_Connect();
        $this->conn = $db->connect();


    }

    // destructor
    function __destruct() {
        
    }

    /**
     * Storing new user
     * returns user details
     */
    public function storeUser($name, $email, $password) {
        $uuid = uniqid('', true);
        $hash = $this->hashSSHA($password);
        $encrypted_password = $hash["encrypted"]; // encrypted password
        $salt = $hash["salt"]; // salt

        $stmt = $this->conn->prepare("INSERT INTO users(unique_id, name, email, encrypted_password, salt, created_at,lastloggedin) VALUES(?, ?, ?, ?, ?, NOW(),NULL)");
        $stmt->bind_param("sssss", $uuid, $name, $email, $encrypted_password, $salt);
        $result = $stmt->execute();
        $stmt->close();

        // check for successful store
        if ($result) {
            $stmt = $this->conn->prepare("SELECT * FROM users WHERE email = ?");
            $stmt->bind_param("s", $email);
            $stmt->execute();
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();

            return $user;
        } else {
            return false;
        }
    }

    /**
     * Get user by email and password
     */
    public function getUserByEmailAndPassword($email, $password) {

        $stmt = $this->conn->prepare("SELECT * FROM users WHERE email = ?");

        $stmt->bind_param("s", $email);

        if ($stmt->execute()) {
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            return $user;
        } else {
            return NULL;
        }
    }

    /**
     * Check user is existed or not
     */
    public function isUserExisted($email) {
        $stmt = $this->conn->prepare("SELECT email from users WHERE email = ?");

        $stmt->bind_param("s", $email);

        $stmt->execute();

        $stmt->store_result();

        if ($stmt->num_rows > 0) {
            // user existed 
            $stmt->close();
            return true;
        } else {
            // user not existed
            $stmt->close();
            return false;
        }
    }

    /**
     * Encrypting password
     * @param password
     * returns salt and encrypted password
     */
    public function hashSSHA($password) {

        $salt = sha1(rand());
        $salt = substr($salt, 0, 10);
        $encrypted = base64_encode(sha1($password . $salt, true) . $salt);
        $hash = array("salt" => $salt, "encrypted" => $encrypted);
        return $hash;
    }

    /**
     * Decrypting password
     * @param salt, password
     * returns hash string
     */
    public function checkhashSSHA($salt, $password) {

        $hash = base64_encode(sha1($password . $salt, true) . $salt);

        return $hash;
    }

    public function lastlogged($timelogged , $email){
        $stmt = $this->conn->prepare("UPDATE users SET lastloggedin= ? WHERE email = ? ");
        $stmt->bind_param("ss", $timelogged, $email);
        $result = $stmt->execute();
        $stmt->close();

        if($result)
            return true;
    }

    public function assetdetails($ASSETCODE)
    {
        $stmt = $link->prepare("SELECT Ecode FROM AssetCodes WHERE ASSETCODE= ?");
        $stmt->bind_param("s", $ASSETCODE);
        $stmt->execute();
        $stmt->bind_result($Ecode);
        $stmt->fetch();

        if($Ecode == '1')
            $AssetName = "Tunnel_Ventilation_Fan";
        else if($Ecode == '2')
            $AssetName = "Tunnel_Ventilation_Damper";

        $stmt->close();

        $stmt = $link2->prepare('SELECT location,installdate FROM ? WHERE ASSETCODE = ?');
        $stmt->bind_param("ss",$AssetName,$ASSETCODE);
        $stmt->execute();
        $stmt->bind_result($AssetLocation,$AssetInstallDate);
        $stmt->fetch();
        $stmt->close();

        $assetdetails = array("AssetName"=>"$", "AssetLocation"=>"37", "AssetInstallDate"=>"43");
    }

}

?>
