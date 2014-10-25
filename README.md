ContactPickerUtil
=================

PhoneGap contact picker plugin


This plugin will help you to open the native contact picker from your PhoneGap application, and pick a contact's name 
and phone number. If the selected contact has multiple numbers, it will ask you to pick one of them.


How to use:

Adobe PhoneGap Build
=====================

Add the following to your config.xml
<pre>
<code>
&lt;gap:plugin name="com.mobitel.nalaka.piccon" &gt;
&lt;/gap:plugin &gt;
</code>
</pre>

Add the included JavaScript into your index.html
<pre>
<code>
&lt;script src="ContactPicker.js"/ &gt;

</code>
</pre>


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
<pre> 
 <code>
    &lt;feature name="ContactPick" &gt;
        &lt; param name="android-package" value="com.mobitel.nalaka.piccon.ContactPick" / &gt;
    &lt;/feature &gt;
</code>
</pre>

* and add the following to permission to AndroidManifest.xml file
<pre> 
 <code>
    &lt;uses-permission android:name="android.permission.READ_CONTACTS" /&gt;
</code>
</pre>
    
* link the ContactPicker.js to the html file
<pre> 
 <code>
  &lt;script src="ContactPicker.js" /&gt;
</code>
</pre>

Or you can just add the plugin script to your page yourself by adding following code on <b>deviceready</b> function.

<pre>
<code>

    window.contactPickerUtil = function(callback) {
    cordova.exec(callback, function(err) {
        console.log(err);
    }, "ContactPickerUtil", "pick", []);

</code>
</pre>


USAGE
=======

example :
<code>
<pre>

&lt;html&gt;
    &lt;head>
        &lt;meta charset="utf-8" /&gt;
        &lt;script type="text/javascript" src="cordova.js"/ &gt;
        &lt;script type="text/javascript" src="ContactPicker.js"/ &gt;
        
        &lt;script type="text/javascript" &gt;
           
            function pickAContact(){
                window.contactPickerUtil(function(data) {
                    alert("picked contact is :"+data.number+" - "+data.name);
                });
            }

        &lt;/script&gt;
    &lt;/head&gt;
    &lt;body&gt;

        


    &lt;div &gt;

        &lt; button type="button"  onclick="pickAContact()" &gt; Click To Pick &lt;/button&gt;

    &lt;/div&gt;

    &lt;/body&gt;
&lt;/html&gt;

</code>
</pre>






