var stompClient = null;
var roomId = [[${roomId}]];
var chatList = [[${chatList}]];

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/ws-stomp');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/room/'+roomId, function (chatMessage) {
            showGreeting(JSON.parse(chatMessage.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

//html 에서 입력값, roomId 를 받아서 Controller 로 전달
function sendChat() {
    stompClient.send("/send/"+roomId, {},
        JSON.stringify({
            'sender': $("#sender").val(),
            'message' : $("#message").val()
        }));
}

//저장된 채팅 불러오기
function loadChat(chatList){
    if(chatList != null) {
        for(chat in chatList) {
            $("#chatting").append(
                "<tr><td>" + "[" + chatList[chat].sender + "]" + chatList[chat].message + "</td></tr>"
            );
        }
    }
}

//보낸 채팅 보기
function showChat(chatMessage) {
    $("#chatting").append(
        "<tr><td>" + "[" + chatMessage.sender + "]" + chatMessage.message + "</td></tr>"
    );
}

function sendName() {
    stompClient.send("/chat/"+roomId, {},
        JSON.stringify({
            'roomId' : roomId,
            'name': $("#name").val(),
            'message' : $("#message").val()
        }));
}

function showGreeting(chatMessage) {
    console.log(chatMessage.name)
    $("#chatting").append("<tr><td>" + "[" + chatMessage.name + "]" + chatMessage.message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});