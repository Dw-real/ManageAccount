function logIn() {
    const userId = document.getElementById("userId").value;
    const userPwd = document.getElementById("userPwd").value;
    const inputNotice = document.getElementById("inputNotice");

    if (!userId) {
        inputNotice.textContent = "아이디를 입력해주세요.";
    } else if (!userPwd) {
        inputNotice.textContent= "비밀번호를 입력해주세요.";
    }

    const formData = {
        id : userId,
        pwd : userPwd
    };

    $.ajax({
        type: 'post',
        url: '/accounts/login',
        contentType:'application/json',
        data:JSON.stringify(formData),
        success: function(response) {
            sessionStorage.setItem('userName', response.name);
            sessionStorage.setItem('userId', response.id);
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
        }
    });
}