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
        },
        success : function (data){
            console.log(data + "성공");

        },
        error : function (){
            console.log("에러");
        }
    });
}

$(document).ready(function () {
    //글제목 클릭 ajax
    $('.artHead').on("click", function () {

        let Id = $(this).val();

        $.ajax({
            type : "POST",
            url : "/selectArt",
            data : {
                artId: Id,
            },
            success: function (data) {
                console.log(data);
                $('#firstDiv').empty().append(data);

                //댓글 작성 클릭
                $('#insertComBtn').on("click", function () {
                    let thisArtId = $(this).val();
                    insertComAjax(thisArtId);
                })

            },
            error: function () {
                console.log("에러");
            }

        });
    });


})
