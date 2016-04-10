Building based on the previous project on my github(simple calculator in Android repo), implementing the functionality of a calculator app with the UI below.

(again, no need for the menu). This is the UI of the scientific calc app in MS Windows. The functionalities
that have been outlined in red need not be implemented.

In addition, provide a “history” button in the calculator app that will take the user to a new activity,
which will display a history of all computations and events such as below:

2*7 = 14

(20+30)*2+21 = 121

Memory save: 100

5^2 = 25

Memory cancel.

Note that the activity can get destroyed at any time. So make sure to store the data when the user uses
the memory functions such as MS, M+ and M- such that you can recover the data when the activity is
created again. Also, you need to save the history and recover across multiple create-destroy cycles.


![image](https://cloud.githubusercontent.com/assets/14539985/14408649/67b01d80-fec3-11e5-9f26-5fe3df17f464.png)


