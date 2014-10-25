ContactPickerUtil
=================

phonegap contact picker plugin


This plugin will help you to open native contact picker from your phonegap application and pick contacts name 
and contact number.If the selected contact number have more than one contact it will asked your to pick one of them.


How to use:

Adobe PhoneGap Build
=====================

Add the following to your config.xml

```xml
<gap:plugin name="com.mobitel.nalaka.piccon">
</gap:plugin>
```

Add the javascript scripts included in the plugin into your index.html

```html
<script src="ContactPicker.js"/>
```

Manual Android Installation
=============================


* Add the source file to the following structure

<pre>

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


</pre>

* and add the following to config.xml file

```xml
<feature name="ContactPick">
    <param name="android-package" value="com.mobitel.nalaka.piccon.ContactPick" />
</feature>
```

* and add the following to permission to AndroidManifest.xml file

```xml
<uses-permission android:name="android.permission.READ_CONTACTS" />
```
    
* link the ContactPicker.js to the html file

```html
<script src="ContactPicker.js" />
```

Or you can just add the plugin script to your page yourself by adding following code on <b>deviceready</b> function.

```js
window.contactPickerUtil = function(callback) {
cordova.exec(callback, function(err) {
    console.log(err);
}, "ContactPickerUtil", "pick", []);
```

USAGE
=======

example :
```html
<html>
    <head>
        <meta charset="utf-8" />
        <script type="text/javascript" src="cordova.js"/>
        <script type="text/javascript" src="ContactPicker.js"/>
        
        <script type="text/javascript">
           
            function pickAContact(){
                window.contactPickerUtil(function(data) {
                    alert("picked contact is :"+data.number+" - "+data.name);
                });
            }

        </script>
    </head>
    <body>

        


    <div>

        <button type="button"  onclick="pickAContact()"> Click To Pick </button>

    </div>

    </body>
</html>
```
