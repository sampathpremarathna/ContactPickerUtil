/**
 * Created with IntelliJ IDEA.
 * User: nalakap
 * Date: 8/15/13
 * Time: 7:01 PM
 */

var argscheck = require('cordova/argscheck'),
    exec = require('cordova/exec');
/*
 * this function will open native contact picker window and let user to pick contact
 * @param callback function to be called after successfull call 
 * @return The Contact object which user picked .
 * ex : function successCallBack(data){
 *               console.log(data.name);
 *               console.log(data.number);
 *      }
 */
	
window.contactPickerUtil = function(callback) {
    cordova.exec(callback, function(err) {
        console.log(err);
    }, "ContactPickerUtil", "pick", []);
};

