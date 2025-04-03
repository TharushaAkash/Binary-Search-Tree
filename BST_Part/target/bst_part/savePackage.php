<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $json = file_get_contents("php://input");
    $data = json_decode($json, true);

    if ($data) {
        $newPackage = uniqid() . ", " . $data["name"] . ", " . $data["price"] . ", " . $data["duration"] . "\n";
        file_put_contents("packages.txt", $newPackage, FILE_APPEND); // Append package to file

        echo "Package added successfully!";
    } else {
        echo "Invalid package data.";
    }
}
?>
