/**
 * Created with IntelliJ IDEA.
 * User: Sayef
 * Date: 16/10/2016
 * Time: 04:56 PM
 * To change this template use File | Settings | File Templates.
 */

function TimeFieldHandler(timeFieldId, timeFormat) {
    this.timeFieldId = timeFieldId;
    this.timeFormat = timeFormat;
    this.hour = timeFieldId + 'Hour';
    this.min = timeFieldId + 'Min';
    this.ampm = timeFieldId + 'AMPM';
    this.hourText = timeFieldId + 'HourText';
    this.minText = timeFieldId + 'MinText';
    this.ampmText = timeFieldId + 'AMPMText';
    /*
     * Creates dropdown for time field
     */
    this.createTimeField = function(timeFieldHandler) {

        /* Use 00 - 23 for 24-hours format, not 01 - 24. Use 01 - 12 for 12-hours format */
        var hourArray, minutesArray, ampmArray;

        if(timeFieldHandler.timeFormat == "12") {

            hourArray = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'];
            ampmArray = [ 'AM', 'PM'];

        }else if(timeFieldHandler.timeFormat == "24") {

            hourArray = ['00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11',
                '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23'];
        }

        minutesArray    = [ '00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12',
            '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25',
            '26', '27', '28', '29', '30', '31', '32', '33', '34', '35', '37', '38', '39',
            '40', '41', '42', '43', '44', '45', '46', '47', '48', '49', '50', '51', '52',
            '53', '54', '55', '56', '57', '58', '59'];



        $.each(hourArray, function (i, val) {
            $(timeFieldHandler.hour).append('<option value="' + val + '">' + val + '</option>');
        });

        $.each(minutesArray, function (i, val) {
            $(timeFieldHandler.min).append('<option value="' + val + '">' + val + '</option>');
        });

        if(timeFieldHandler.timeFormat == "12") {
            $.each(ampmArray, function (i, val) {
                $(timeFieldHandler.ampm).append('<option value="' + val + '">' + val + '</option>');
            });
        }
    }


    /**
     * Set Hour, Minute, AmPm in separate Id.
     */
    this.initHourMinuteAmPm = function(timeFieldHandler) {
        if($(timeFieldHandler.timeFieldId).val()) {
            $(timeFieldHandler.hour).val($(timeFieldHandler.timeFieldId).val().substring(0, 2));
            $(timeFieldHandler.hourText).val($(timeFieldHandler.timeFieldId).val().substring(0, 2));
            $(timeFieldHandler.min).val($(timeFieldHandler.timeFieldId).val().substring(3, 5));
            $(timeFieldHandler.minText).val($(timeFieldHandler.timeFieldId).val().substring(3, 5));
            if (timeFieldHandler.timeFormat == "12") {
                $(timeFieldHandler.ampm).val($(timeFieldHandler.timeFieldId).val().substring(6, 8));
                $(timeFieldHandler.ampmText).val($(timeFieldHandler.timeFieldId).val().substring(6, 8));
            }

        }else {
            $(timeFieldHandler.hour).val("12");
            $(timeFieldHandler.hourText).val("12");
            $(timeFieldHandler.min).val("00");
            $(timeFieldHandler.minText).val("00");
            if (timeFieldHandler.timeFormat == "12") {
                $(timeFieldHandler.ampm).val("AM");
                $(timeFieldHandler.ampmText).val("AM");
            }
        }
        var temp = $(timeFieldHandler.hour).val() + ':' + $(timeFieldHandler.min).val();
        if(timeFieldHandler.timeFormat=="12") temp = temp + " " + $(timeFieldHandler.ampm).val();
        $(timeFieldHandler.timeFieldId).val(temp);
    }

    /*
     * Change in dropdown values sets new time in the timeField
     */

    this.onChangeTimeFieldInput = function(timeFieldhandler, attr, val) {
        if(attr.search("Hour")!=-1) attr = "Hour";
        else if(attr.search("Min")!=-1) attr = "Min";
        else if(attr.search("AMPM")!=-1) attr = "AMPM";

        var temp;

        if(attr == "Hour") {

            temp = val + ':' + $(timeFieldhandler.minText).val();
            if (timeFieldhandler.timeFormat == "12") temp = temp + " " + $(timeFieldhandler.ampmText).val();

        }else if(attr == "Min"){

            temp = $(timeFieldhandler.hourText).val() + ':' + val;
            if(timeFieldhandler.timeFormat=="12") temp = temp + " " + $(timeFieldhandler.ampmText).val();

        }else if(attr == "AMPM"){

            temp = $(timeFieldhandler.hourText).val() + ':' + $(timeFieldhandler.minText).val() + " " + val;

        }
        $(timeFieldhandler.timeFieldId).val(temp);
    }


    this.onChangeFunctions = function(timeFieldHandler){

        $(timeFieldHandler.hour).on('change', function(){
            var attr =  $(this).attr("id"), val =  $(this).val();
            timeFieldHandler.onChangeTimeFieldInput(timeFieldHandler, attr, val);
            $(timeFieldHandler.hourText).val(val);
        });
        $(timeFieldHandler.min).on('change', function(){
            var attr =  $(this).attr("id"), val =  $(this).val();
            timeFieldHandler.onChangeTimeFieldInput(timeFieldHandler, attr, val);
            $(timeFieldHandler.minText).val(val);
        });
        $(timeFieldHandler.ampm).on('change', function(){
            var attr =  $(this).attr("id"), val =  $(this).val();
            timeFieldHandler.onChangeTimeFieldInput(timeFieldHandler, attr, val);
            $(timeFieldHandler.ampmText).val(val);
        });

        $(timeFieldHandler.hourText).on('change', function(){
            var attr =  $(this).attr("id"), val =  $(this).val();
            if(val.length==1) val = '0' + val;
            timeFieldHandler.onChangeTimeFieldInput(timeFieldHandler, attr, val);
            $(timeFieldHandler.hour).val(val);
        });
        $(timeFieldHandler.minText).on('change', function(){
            var attr =  $(this).attr("id"), val =  $(this).val();
            if(val.length==1) val = '0' + val;
            timeFieldHandler.onChangeTimeFieldInput(timeFieldHandler, attr, val);
            $(timeFieldHandler.min).val(val);
        });
        $(timeFieldHandler.ampmText).on('change', function(){
            var attr =  $(this).attr("id"), val =  $(this).val();
            if(val=='am') val = 'AM';
            else if(val=='pm') val = 'PM';
            timeFieldHandler.onChangeTimeFieldInput(timeFieldHandler, attr, val);
            $(timeFieldHandler.ampm).val(val);
        });
    }
}



/*
 * Not using now
 * Converts 24-hour date type data to 12-hour string type data
 * */
function getCurrentTime(date) {
    var hours = date.getHours();
    var minutes = date.getMinutes();
    var ampm = hours >= 12 ? 'PM' : 'AM';
    hours = hours % 12;
    hours = hours ? hours : 12; // the hour '0' should be '12'
    hours = hours < 10 ? '0'+hours : hours;
    minutes = minutes < 10 ? '0'+minutes : minutes;
    var strTime = hours + ':' + minutes + ' ' + ampm;
    return strTime;
}

// Time related JS ends -------- **** --------------
