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

    // userName 클릭 시 account-options 표시/숨김 토글
    const userNameElement = document.getElementById("userName");
    const accountOptions = document.getElementById("account-options");

    userNameElement.addEventListener('click', function() {
        if (accountOptions.style.display === "none" || accountOptions.style.display === "") {
            accountOptions.style.display = "block";
        } else {
            accountOptions.style.display = "none";
        }
    });
};

// 계정 탈퇴 버튼 클릭 시 처리
const deleteAccount = document.getElementById("deleteAccount");
if (deleteAccount) {
    deleteAccount.addEventListener("click", function() {
        const userId = sessionStorage.getItem('userId'); // 로그인된 userId 가져오기
        const confirmDelete = confirm("정말 탈퇴하시겠습니까?");
        if (confirmDelete && userId) {
            $.ajax({
                type: 'DELETE',
                url: `/accounts/${encodeURIComponent(userId)}`,
                success: function(response) {
                    sessionStorage.removeItem('userId'); // 로그인 상태 제거
                    sessionStorage.removeItem('userName');
                    location.reload(); // 페이지 새로고침
                },
                error: function(xhr, status, error) {
                    try {
                        // 서버에서 반환된 오류 메시지를 추출하여 alert로 표시
                        const errorResponse = JSON.parse(xhr.responseText);
                        alert(errorResponse.errors.join("\n"));
                    } catch (e) {
                        alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
                    }
                }
            });
            alert("탈퇴되었습니다.");
        } else {
            alert("탈퇴할 수 없습니다. 다시 시도해주세요.");
        }
    });
}