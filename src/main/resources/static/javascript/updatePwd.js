function updatePwd() {
    const currentPwd = document.getElementById('currentPwd').value;
    const newPwd = document.getElementById('newPwd').value;
    const checkNewPwd = document.getElementById('checkNewPwd').value;
    const notice = document.getElementById('notice');
    const userId = sessionStorage.getItem('userId');

    console.log(userId);
    if (!currentPwd || !newPwd || !checkNewPwd) {
        notice.textContent = "모든 칸을 입력해주세요."
        return;
    } else if (newPwd !== checkNewPwd) {
        notice.textContent = "새 비밀번호를 정확히 입력해주세요.";
        return;
    }

    $.ajax({
        type: 'patch',
        url: `/accounts/${encodeURIComponent(userId)}`,
        contentType: 'application/json',
        data: JSON.stringify({
            currentPwd: currentPwd,
            newPwd: newPwd
        }),
        success: function(response) {
            location.href ="../html/home.html";
        },
        error: function(error) {
            if (error.status == 400 || error.status == 401) {
                alert(error.message);
                return;
            }
        }
    });
}