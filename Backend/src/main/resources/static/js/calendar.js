var clickable = true;
var offset = 0;
var ERROR="Sorry, there was an error. The operation did not succeed.";
var SUCCESS="Success. The operation was completed!";

function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("did", ev.target.getAttribute("data-id"));
}

function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("did");
    date =  ev.target.getAttribute("data-id");
    editDateReq(data,date);
}

function changeMonth(e){
    var firstday = ""+document.getElementById("calendar").getAttribute("data-id").replace(/ /g,"-");
    offset = e;
    $.ajax({
        url: "/changemonth/"+e+"/"+firstday,
        type: "GET",
        dataType : "html",
        success: function( data) {
            jsonDecoded = JSON.parse(data);
            $('#calendardiv').replaceWith($('#calendardiv').html(jsonDecoded["cal"]));
            $('#monthyear').text(jsonDecoded["header"]);
        },
        error: function( xhr, status ) {
            alert( "Sorry, there was a problem!" );
        }
    });
}


function input(){
    var date= document.getElementById("date-data").innerHTML;
    var text=document.getElementById("text-data").value;
    var note=document.getElementById("note-data").value;
    var time=document.getElementById("time-data").value;
    inputReq(date,text,note,time);
}


function dateClick(e){
    var firstday = ""+e.getAttribute("data-id");
    document.getElementById("date-data").innerHTML = firstday;
    var text=document.getElementById("text-data").value = "";
    var note=document.getElementById("note-data").value = "";
    document.getElementById("time-data").value = "12:00:00";
    document.getElementById("input-btn").onclick = function() {
        input();
    };
    $('#inputModal').modal('show');
    document.getElementById("edit-btn").style.visibility = "hidden";
}


function inputReq(date, text, note, time){
    datax = {"date" : date, "text" : text, "note" : note,"time" : time};
    $.ajax({
        url: "/input",
        type: "POST",
        contentType : 'application/json',
        data : JSON.stringify(datax, null, '\t'),
        success: function( data) {
            if(data == "success"){
             alert(SUCCESS);
            }
            else{
                alert(ERROR);
            }
            $('#inputModal').modal('hide');
            changeMonth(0);
        },
        error: function( xhr, status ) {
            alert( "Sorry, there was a problem!" );
        }
    });
}



function editSchedule(e){
    var index = ""+e.getAttribute("data-id");
    $.ajax({
        url: "/schedule/"+index,
        type: "GET",
        dataType : "html",
        success: function( data) {
            jsonDecoded = JSON.parse(data);
            document.getElementById("edit-date-data").innerHTML = jsonDecoded["date"];
            document.getElementById("edit-item-data").value = jsonDecoded["text"];
            document.getElementById("edit-note-data").value = jsonDecoded["note"];
            document.getElementById("edit-time-data").value = jsonDecoded["time"];
            document.getElementById("edit-note-btn").onclick = function() {
                note = document.getElementById("edit-note-data").value;
                editNoteReq(index,note);
            };
            document.getElementById("edit-item-btn").onclick = function() {
                note = document.getElementById("edit-item-data").value;
                editItemReq(index,note);
            };

            document.getElementById("edit-time-btn").onclick = function() {
                time = document.getElementById("edit-time-data").value;
                editTimeReq(index,time);
            };

            document.getElementById("delete-item-btn").onclick = function() {
                            removeItemReq(index);
            };
            $('#editModal').modal('show');
        },
        error: function( xhr, status ) {
            alert( "Sorry, there was a problem!" );
        }
    });
}




function removeItemReq(id){
 datax = {"id" : id};
    $.ajax({
        url: "/remove",
        type: "DELETE",
        contentType : 'application/json',
        data : JSON.stringify(datax, null, '\t'),
        success: function( data) {
            if(data === "success"){
                alert(SUCCESS);
            }else{
                alert(ERROR)
            }
            $('#inputModal').modal('hide');
            changeMonth(0);
        },
        error: function( xhr, status ) {
           alert( "Sorry, there was a problem!" );
        }
  });
}

function editNoteReq(id, note){

    datax = {"id" : id, "note" : note};
    $.ajax({
        url: "/updateNote",
        type: "POST",
        contentType : 'application/json',
        data : JSON.stringify(datax, null, '\t'),
        success: function( data) {
             if(data === "success"){
                alert(SUCCESS);
             }else{
                alert(ERROR)
             }
            $('#inputModal').modal('hide');
            changeMonth(0);
        },
        error: function( xhr, status ) {
           alert( "Sorry, there was a problem!" );
        }
  });
}

function editDateReq(id, date){

    datax = {"id" : id, "date" : date};
    $.ajax({
        url: "/updateDate",
        type: "POST",
        contentType : 'application/json',
        data : JSON.stringify(datax, null, '\t'),
        success: function( data) {
             if(data === "success"){
                alert(SUCCESS);
             }else{
                alert(ERROR)
             }
            $('#inputModal').modal('hide');
            changeMonth(0);
        },
        error: function( xhr, status ) {
           alert( "Sorry, there was a problem!" );
        }
  });
}

function editTimeReq(id, time){

    datax = {"id" : id, "time" : time};
    $.ajax({
        url: "/updateTime",
        type: "POST",
        contentType : 'application/json',
        data : JSON.stringify(datax, null, '\t'),
        success: function( data) {
            if(data === "success"){
                alert(SUCCESS);
            }else{
                alert(ERROR)
            }
            $('#inputModal').modal('hide');
            changeMonth(0);
        },
        error: function( xhr, status ) {
           alert( "Sorry, there was a problem!" );
        }
  });
}

function editItemReq(id, item){

    datax = {"id" : id, "item" : item};
    $.ajax({
        url: "/updateItem",
        type: "POST",
        contentType : 'application/json',
        data : JSON.stringify(datax, null, '\t'),
        success: function( data) {
            if(data === "success"){
                alert(SUCCESS);
            }else{
                alert(ERROR)
            }
            $('#inputModal').modal('hide');
            changeMonth(0);
        },
        error: function( xhr, status ) {
           alert( "Sorry, there was a problem!" );
         }
    });
}


function deleteAll()
{
    //double check with user
    $('#deleteAllModal').modal('show');
}

function deleteAllAfterCheck()
{
    $.ajax({
        url: "/deleteAll",
        type: "DELETE",
        success: function( data) {
            if(data === "success"){
                alert(SUCCESS);
            }else{
                alert(ERROR);
            }
            $('#deleteAllModal').modal('hide');
            changeMonth(0);
        },
        error: function( xhr, status ) {
            alert( "Sorry, there was a problem!" );
        }
    });
}