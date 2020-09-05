# FirebaseExpenseApp  

## Part A: ExpenseApp screen
The main activity should start by displaying the ExpenseApp screen, with the following requirements:
1. When the app first starts, there should be no expenses added in the list. So it should
display the ExpenseApp screen with a message, “There is no expense to show,
Please add your expenses from the menu.”.  
2. The list of expenses should be stored in Firebase realtime database.  
3. The ExpenseApp screen should use a ListView/RecyclerView to display the list of
expenses.  
a) Long press on an item should delete the expense from the list. It should update
Firebase, and refresh the ListView to indicate this change. A Toast should be
displayed having the message, “Expense Deleted”.  
b) Clicking on an expense item should display the ShowExpense screen, you
should push the ExpenseApp screen on the screen stack.  
4. Clicking on the add (+) icon should start the AddExpense screen.  

## Part B: AddExpense screen  
This screen should enable user to add a new expense. You should complete the
following tasks:
1. The user should be able to enter the expense name, category and amount. The app
should take the current date as the expense date. This information should be stored
in an Expense Object.
2. The categories should be in a selection pane . The categories should include are: Groceries, Invoice, Transportation, Shopping, Rent, Trips,
Utilities and Other.  
3. Clicking on “Add Expense” button should validate the user’s input and ensure that
all the fields are provided. If any field is missing, display a Toast to indicate the
missing field. If all the fields are provided correctly, save the fields as an Expense
object, and add the new expense to Firebase. Then display the main activity with the
added expense.

## Part C: ShowExpenses screen  
Implement the following requirements:
1. When the user clicks on an expense item in the ExpenseApp screen, the
ShowExpenses screen should be started to show the details of selected expense
item.
2. If the user clicks Edit Expense button, it should start EditExpense screen with
preloaded values.
3. Upon clicking Close button, the screen should be closed and should navigate back
to the ExpenseApp screen.

## Part D: EditExpense screen  
Implement the following requirements:
1. It is identical to the Add Expense screen with preloaded values for the particular
expense.
2. If the user makes changes to the values, and clicks on Save button, it should update
the corresponding values to Firebase, and get back to the main screen with updated
values.
