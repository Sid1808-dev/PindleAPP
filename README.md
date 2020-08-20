# PindlePay ( A new way to pay)
# PINDLE stands for :
# Payment Infrastructure for Dynamic Link Entwining
As the name clearly suggests :
Our aim is to make the whole payment process hassle free and much more secure for the end users.

# UI/UX - 
Talking about the problem with the UI of current apps in the market, we are developing a solution to that by replicating the complete process of physical transaction into mobile application by issuing a virtual Credit/Debit card to users. Hence, making the UX similar to the physical means of payment.

To eliminate the issue of going through the hassle of running the app for payment, our concentration will be to design the single or multiple Home Screen Widget(s), a physical card look-alike, protected by biometric means (i.e. fingerprint scanner).
This solution will drastically speed up the entire process.

# SECURITY - 
Hopping over to solving the security issues with currently available Credit/Debit cards.
Our “Dynamic Payments Card” Protocol will be changing the card number randomly in the time of every particular transaction.

So a credit/debit card holder doesn’t own a particular serial number hence making the
cloning and information theft, practically infeasible. 
We believe this technology could be a disruptive product in the currently wide market of EMV (Europay, MaterCard, Visa) Protocol which is used by card issuing companies.


# A walkthrough of the app Interface

# Login
The login to the app is made secure using fingerprint (Biometric) authentication. After you jum to the bank account login screen if you are a new user .

![login](https://user-images.githubusercontent.com/60344472/90718746-5c6ddc00-e2d0-11ea-9371-d74aff289bdd.gif)

# Adding Banking credentials
The user after biometric authentication can save his/her profile and add a username and the Original Account Name issued by the bank

![main](https://user-images.githubusercontent.com/60344472/90724892-e91e9700-e2dc-11ea-96d3-d87d4d9c6d9d.gif)

# Security - Generating a unique card number at  each payment
The profile of the user is saved on to the card that is displayed.
Everytime the user tries to make a payment, clicking the regenerate card button calls a random 16-digit number which is linked to the actual
account number of the customer in the database. The generated number becomes useless after a payment is made.
# To note:
So if a fraudster tries to access the account with that number he/she cannot do so.

![Bank](https://user-images.githubusercontent.com/60344472/90727013-3d774600-e2e0-11ea-937c-d5ed13f2c589.gif)

# Payment
After the card number is generated you can make payment using that card number. Once the payment is made this card number is disabled and new number is generated

![Pay](https://user-images.githubusercontent.com/60344472/90728623-d8711f80-e2e2-11ea-8a50-dd3e6b4b4a5c.gif)

# Making payment hassle free
To make the payment process as simple as possible so that more and more users can make use of the platform, we have also added widget functionality to the the homescreen.
Clicking the widget lets you access the  card and you can genererate a new card number to make the payment

![rename](https://user-images.githubusercontent.com/60344472/90729685-934ded00-e2e4-11ea-8ab6-02f361849c7c.gif)

# What works behind the scenes (Backend Algorithm working) 

The transaction data and connection with Banks is made through UPI

Our System Architecture:
Two Basic layers of Database
 1. Keystore
 2. Pindle Library and API

Freedom to use much more secure protocols than ‘Luhn Algo/Mod-10’ like SHA-1 Fuctions

![sysarch](https://user-images.githubusercontent.com/60344472/90752218-be403d00-e2f4-11ea-9886-d1efc3152617.png)

# Schematic




As we are currently actively working over the algorithm, logically there will be a few changes in the approach but we are working day and night to make the end product aligned with our vision.
We will be going through the process of filing a patent only after which the algorithm’s details and approach could be disclosed.






