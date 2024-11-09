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
            alert("비밀번호가 변경되었습니다.");
            location.href ="../html/home.html";
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