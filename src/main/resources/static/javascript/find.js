function findUserId() {
    const name = document.getElementById("userName").value;
    const phoneNumber = document.getElementById("userPhoneNumber").value;

    const showUserInfoDiv = document.getElementById("show-userInfo");
    const foundUserId = document.getElementById("foundUserId");

    if (!name || !phoneNumber) {
        alert("이름, 전화번호를 모두 입력해주세요");
        return;
    }

    $.ajax({
        type: "get",
        url:  `/accounts/${encodeURIComponent(name)}/${encodeURIComponent(phoneNumber)}`,
        success: function(response) {
            if (response && response.id) {
                showUserInfoDiv.style.display = "block";
                foundUserId.textContent = response.id;
            } else {
                alert("회원 정보를 가져오는 중 예상치 못한 문제가 발생했습니다.");
            }
        },
        error: function(error) {
            if (error.status == 404) {
                alert("해당 정보로 가입된 id가 없습니다.");
                return;
            }
            else {
                alert("오류가 발생했습니다.");
            }
        }
    });
}

function findUserPwd() {
    const id = document.getElementById("userId").value;

    const showUserInfoDiv = document.getElementById("show-userInfo");
    const foundUserPwd = document.getElementById("foundUserPwd");

    if (!id) {
        alert("아이디를 입력하세요");
        return;
    }

    $.ajax({
        type: "get",
        url:  `/accounts/${encodeURIComponent(id)}`,
        success: function(response) {
            if (response && response.id) {
                showUserInfoDiv.style.display = "block";
                foundUserPwd.textContent = response.pwd;
            } else {
                alert("회원 정보를 가져오는 중 예상치 못한 문제가 발생했습니다.");
            }
        },
        error: function(error) {
            if (error.status == 404) {
                alert(error.message);
                return;
            }
            else {
                alert("오류가 발생했습니다.");
            }
        }
    });
}

function checkComplete() {
    const showUserInfoDiv = document.getElementById("show-userInfo");
    showUserInfoDiv.style.display = "none";
}

