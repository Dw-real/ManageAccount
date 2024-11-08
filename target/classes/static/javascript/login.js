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
        error: function(error) {
            console.log("로그인 실패");
            if (error.status == 400 || error.status == 401) {
                alert(error.message);
                return;
            }
        }
    });
}