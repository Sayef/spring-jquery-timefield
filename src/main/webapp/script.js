$(document).ready(function () {
    $("#registrations").addClass("current");

    /*
     * Time related JS starts here
     *--------- **** ----------------*/

    var timeFieldId = "#crimeTime";
    var timeFormat = "${timeFormat}";

    var timeFieldHandler = new TimeFieldHandler(timeFieldId, timeFormat);
    timeFieldHandler.createTimeField(timeFieldHandler);
    timeFieldHandler.initHourMinuteAmPm(timeFieldHandler);
    timeFieldHandler.onChangeFunctions(timeFieldHandler);

    $('#myFormId').validate({
        ignore: ':hidden:not(.time-input), .time-input-control'
    });
}