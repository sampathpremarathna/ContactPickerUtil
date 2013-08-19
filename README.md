ContactPickerUtil
=================

phonegap contact picker plugin


This plugin will help you to open native contact picker from your phonegap application and pick contacts name 
and contact number.If the selected contact number have more than one contact it will asked your to pick one of them.


How to use:

Adobe PhoneGap Build
=====================

Add the following to your config.xml

<gap:plugin name="com.mobitel.nalaka.piccon.ContactPick">
</gap:plugin>

Add the javascript scripts included in the plugin into your index.html

<script src="ContactPicker.js"></script>


Manual Android Installation
=============================


* Add the source file to the following structure

|_Project
|  |_assets
|    |_www
|      |_index.html
|      |_ContactPicker.js
|      |_cordova.js
|
|
|  |_src
|    |_com.mobitel.nalaka.piccon
|      |_ContactPick.java
|      |_ContactResult.java
|
|
|//other files with standerd structure


* and add the following to config.xml file

    <feature name="ContactPick">
        <param name="android-package" value="com.mobitel.nalaka.piccon.ContactPick" />
    </feature>
    
* link the _ContactPicker.js to the html file
  
  <script src="ContactPicker.js"></script>


USAGE
=======

example :


<html>
    <head>
        <meta charset="utf-8" />
        <script type="text/javascript" src="cordova.js"></script>
        <script type="text/javascript" src="ContactPicker.js"></script>
        
        <script type="text/javascript">
            function pickAContact(){
                window.contactPickerUtil(function(data) {
                    alert("picked contact is :"+data.number+" - "+data.name);
                });
            }

        </script>
    </head>
    <body>

        


    <div >
        <input id="aa" type="text" />

        <button type="button"  onclick="pickAContact()">Click To Pick</button>

    </div>

    </body>
</html>








