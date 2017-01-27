<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration success</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<article>
    <header><h3>Registration has been successfully completed!</h3></header>
    <section>
        <table>
            <tbody>
            <tr>
                <td><strong>First name</strong></td>
                <td>${firstname}</td>
            </tr>
            <tr>
                <td><strong>Last name</strong></td>
                <td>${lastname}</td>
            </tr>
            <tr>
                <td><strong>E-mail</strong></td>
                <td>${email}</td>
            </tr>
            <tr>
                <td><strong>Phone</strong></td>
                <td>${phone}</td>
            </tr>
            <tr>
                <td><strong>Organization</strong></td>
                <td>${organization}</td>
            </tr>
            <tr>
                <td><strong>Address</strong></td>
                <td>${address}</td>
            </tr>
            <tr>
                <td><strong>URL Homepage</strong></td>
                <td>${urlpage}</td>
            </tr>
            <tr>
                <td><strong>City</strong></td>
                <td>${city}</td>
            </tr>
            <tr>
                <td><strong>Zip code</strong></td>
                <td>${zipcode}</td>
            </tr>
            <tr>
                <td><strong>Payment mode</strong></td>
                <td>${paymentmode}</td>
            </tr>
            <tr>
                <td><strong>Total to pay(&euro;)</strong></td>
                <td>${total}</td>
            </tr>
            <tr>
                <td><strong>Start date</strong></td>
                <td>${startdate}</td>
            </tr>
            <tr>
                <td><strong>Favorite color</strong></td>
                <td>${favoritecolor}</td>
            </tr>
            <tr>
                <td><strong>Favorite color</strong></td>
                <td>${abc}</td>
            </tr>
            </tbody>
        </table>
    </section>
    <footer>Thanks for subscribing!</footer>
</article>
</body>
</html>