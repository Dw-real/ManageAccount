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
}

function checkComplete() {
    const showUserInfoDiv = document.getElementById("show-userInfo");
    showUserInfoDiv.style.display = "none";
}

