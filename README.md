# PindlePay ( A new way to pay)
# A walkthrough of the app Interface


# Login
The login to the app is made secure using fingerprint (Biometric) authentication. After you jum to the bank account login screen if you are a new user .
https://github.com/Sid1808-dev/PindleAPP/issues/1#issue-682411931

![login](https://user-images.githubusercontent.com/60344472/90718746-5c6ddc00-e2d0-11ea-9371-d74aff289bdd.gif)

# Adding Banking credentials
The user after biometric authentication can save his/her profile and add a username and the Original Account Name issued by the bank

![main](https://user-images.githubusercontent.com/60344472/90724892-e91e9700-e2dc-11ea-96d3-d87d4d9c6d9d.gif)

# Generating a unique card number at  each payment
The profile of the user is saved on to the card that is displayed.
Everytime the user tries to make a payment, clicking the regenerate card button calls a random 16-digit number which is linked to the actual
account number of the customer in the database. The generated number becomes useless after a payment is made.
# To note:
So if a fraudster tries to access the account with that number he/she cannot do so.

![Bank](https://user-images.githubusercontent.com/60344472/90727013-3d774600-e2e0-11ea-937c-d5ed13f2c589.gif)

# Payment
After the card number is generated you can make payment using that card number. Once the payment is made this card number is disabled and new number is generated



