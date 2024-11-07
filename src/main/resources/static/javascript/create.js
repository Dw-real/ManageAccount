function checkId() {
    const id = document.getElementById("id").value;
    const checkResult = document.getElementById("check-result");
    console.log(id + "\n");

    // 아이디를 입력하지 않은 경우
    if (!id) {
        checkResult.textContent = "아이디를 입력해주세요.";
        checkResult.style.color = "red";
        return;
    }

    // AJAX 요청
    $.ajax({
        type: "post",
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
