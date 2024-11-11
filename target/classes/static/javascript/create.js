function checkId() {
    const id = document.getElementById("id").value;
    const checkResult = document.getElementById("check-result");

    // 아이디를 입력하지 않은 경우
    if (!id) {
        checkResult.textContent = "아이디를 입력해주세요.";
        checkResult.style.color = "red";
        return;
    }

    // AJAX 요청
    $.ajax({
        type: "GET",
        url: "/accounts/id-check",  // URL 파라미터로 id 전달
        data: {id: id},  // 데이터를 URL 파라미터로 보냄
        success: function(response) {
            if (response == "ok") {
                checkResult.textContent = "사용 가능한 아이디입니다.";
                checkResult.style.color = "green";
            } else {
                checkResult.textContent = "이미 사용 중인 아이디입니다.";
                checkResult.style.color = "red";
            }
        },
        error: function(xhr, status, error) {
            checkResult.textContent = "서버 오류가 발생했습니다. 다시 시도해주세요.";
            checkResult.style.color = "red";
        }
    });
}

$(document).ready(function(){
    $('#createAccountForm').on('submit', function(event) {
        event.preventDefault();

        const name = $('#name').val();
        const id = $('#id').val();
        const pwd = $('#pwd').val();
        const pwdCheck = $('#pwdCheck').val();
        const birthDate = $('#birthDate').val();
        const gender = $('#gender').val();
        const phoneNumber = $('#phoneNumber').val();
        const email = $('#email').val();

        // 비밀번호 확인
        if (pwd !== pwdCheck) {
            alert("비밀번호가 일치하지 않습니다.");
            return;
        }

        $.ajax({
            type: 'post',
            url: '/accounts',
            contentType:'application/json',
            data: JSON.stringify({
                name: name,
                id: id,
                pwd: pwd,
                birthDate: birthDate,
                gender: gender,
                phoneNumber: phoneNumber,
                email:email
            }),
            success: function(response) {
                alert("계정이 생성되었습니다!");
                location.href = "../html/home.html";
            },
            error: function(xhr, status, error) {
                try {
                    // 서버에서 반환된 오류 메시지를 추출하여 alert로 표시
                    const errorResponse = JSON.parse(xhr.responseText);
                    alert(errorResponse.errors.join("\n"));
                } catch (e) {
                    alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
                }
                checkResult.style.color = "red";
                checkResult.textContent = "서버 오류가 발생했습니다. 다시 시도해주세요.";
            }
        });
    });
});