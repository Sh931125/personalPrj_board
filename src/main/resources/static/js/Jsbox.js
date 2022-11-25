let today = new Date();
let attrYear = today.getFullYear(); // 년도
let attrMonth = today.getMonth() + 1;  // 월
let attrDate = today.getDate();  // 날짜
let attrResDate = (attrYear + '-' + attrMonth + '-' + attrDate);



//뷰 전환, 메인
function articlesMain(){
    window.location.href='/';
}

//뷰 전환, 글작성
function insertArtBtn(){
    window.location.href='/create';
}

//글작성 ajax
function insertArtAjax () {
    let createPw = $('#createPw').val();
    let createHead = $('#createHead').val();
    let createBody = $('#createBody').val();

    $.ajax({
        type : "POST",
        url : "/insertArt",
        data : {
            artHead : createHead,
            artBody : createBody,
            artDate : attrResDate,
            artHits : 0,
            artPw : createPw,
        },
        success : function (data){
            console.log("성공");
            articlesMain();

        },
        error : function (){
        console.log("에러");
        }

    });
}
//댓글 입력 ajax
function insertComAjax (e) {
    let comment = $('#commentBody').val();
    let commentPw = $('#commentPw').val();

    $.ajax({
        type : "POST",
        url : "/insertCom",
        data : {
          artId : e,
          comBody : comment,
          comPw : commentPw,
            comDate : attrResDate,
        },
        success : function (data){
            console.log("성공");
                //댓글 입력 후 댓글
            selectArtAjax(e);


        },
        error : function (){
            console.log("에러");
        }
    });
}
//댓글 입력 엔터키
function insertComEnter (e){
    if (e.code == 'Enter'){
        let thisArtId = $('#insertComBtn').val();
        insertComAjax(thisArtId);
    }
}

//글 제목으로 조회 ajax
function selectArtAjax(e){
    $.ajax({
        type : "POST",
        url : "/selectArt",
        data : {
            artId: e,
        },
        success: function (data) {
            //조회수 즉각반영 위해 추가
            selectArtAll();

            $('#firstDiv').empty().append(data);

            //댓글 작성 클릭
            $('#insertComBtn').on("click", function () {
                let thisArtId = $(this).val();
                insertComAjax(thisArtId);
            })
            //댓글삭제 버튼 기능
            $('#deleteComBtn').on("click", function (){
              let thisComId = $(this).val();
              let thisArtId = $('#selectArtId').text();
              deleteComAjax(thisComId, thisArtId);
            })

            //글삭제 버튼 기능
            $('#deleteArtBtn').on("click", function (){
                let thisArtId = $(this).val();
                deleteArtAjax(thisArtId);
            })

        },
        error: function () {
            console.log("에러");
        }
    });
}
//글목록 조회 ajax
function selectArtAll () {
    $.ajax({
        type : "GET",
        url : "/selectArtAll",
        success: function (e) {
            let html = jQuery('<div>').html(e);
            let contents = html.find("div#articlesList").html();
            $('#articlesListDiv').empty().append(contents);
            //글제목 클릭 이벤트
            $('.artHead').on("click", function () {
                let Id = $(this).val();
                plusHitsAjax(Id);
                selectArtAjax(Id);
            });
        },
        error() {
            console.log("error")
        }

    });

}

//조회수 증가 ajax
function plusHitsAjax (e) {
    $.ajax({
        type : "POST",
        url : "/plusHits",
        data : {
            artId: e,
        },
        success: function () {
            console.log("조회수 증가 성공")
        },
        error() {
            console.log("error")
        }
    });
}
//게시글 삭제 ajax
function deleteArtAjax(e){
    $.ajax({
        type : "POST",
        url : "/deleteArt",
        data : {
            artId: e,
        },
        success: function () {
            console.log("성공");
            articlesMain();
        },
        error() {
            console.log("error")
        }
    });
}
//댓글 삭제 ajax
function deleteComAjax(e, e2){
    $.ajax({
        type : "POST",
        url : "/deleteCom",
        data : {
            comId: e,
        },
        success: function () {
            console.log("성공")
            selectArtAjax(e2);
        },
        error() {
            console.log("error")
        }
    });
}

//최초 준비
$(document).ready(function () {
    selectArtAll();
    //dataTable 생성
    // $('#articlesTable').DataTable();

})
