document.getElementById("create").addEventListener('click', function() {
    location.href = "../html/create.html";
});

document.getElementById("login").addEventListener('click', function() {
    location.href = "../html/login.html";
});

document.getElementById("findId").addEventListener('click', function() {
    location.href = "../html/findId.html";
});

document.getElementById("findPwd").addEventListener('click', function() {
    location.href = "../html/findPwd.html";
});

document.getElementById("updatePwd").addEventListener('click', function() {
    location.href = '../html/updatePwd.html';
});

window.onload = function() {
    const loggedIn = sessionStorage.getItem('userId');
    const userName = sessionStorage.getItem('userName');

    if (loggedIn) { // 로그인 성공
        document.getElementById('create').style.display = 'none';
        document.getElementById('login').style.display = 'none';
        document.getElementById('findId').style.display = 'none';
        document.getElementById('findPwd').style.display = 'none';
        document.getElementById('userName').style.display = 'inline';
        document.getElementById('userName').textContent = userName;
        document.getElementById('updatePwd').style.display = 'inline';
        document.getElementById('logout').style.display = 'inline';
    } else {
        document.getElementById('create').style.display = 'inline';
        document.getElementById('login').style.display = 'inline';
        document.getElementById('findId').style.display = 'inline';
        document.getElementById('findPwd').style.display = 'inline';
        document.getElementById('userName').style.display = 'none';
        document.getElementById('updatePwd').style.display = 'none';
        document.getElementById('logout').style.display = 'none';
    }

    // 로그아웃 버튼 클릭 시 처리
    document.getElementById('logout').addEventListener('click', function() {
        sessionStorage.removeItem('userId'); // 로그인 상태 제거
        sessionStorage.removeItem('userName');
        location.reload(); // 페이지 새로고침
    });
}