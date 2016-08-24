/**
 * Created by yanximin on 2016/8/22.
 */
//接口服务器地址
baseUrl = "http://10.108.16.210:8080/MemoryNote/servlet/AddNewNote?"
noteMousedown = function(){
    $("div").removeClass("up_class");
    $(this).addClass("up_class");
}
imgClickUpdate = function(){
    $(this).parent().children("label").attr("contenteditable","true");
    $(this).parent().children("label").focus();
    $(this).parent().children("label").unbind();
    $(this).parent().children("label").blur(function(){
        $(this).attr("contenteditable","false");
        //TODO 向服务器修改笔记内容
        sendPositionMessage($(this).parent())
    });
}
imgClickDelete = function(){
    var Noteid = $(this).parent().children("input:hidden").val();
    $.post(baseUrl+"method=delete",
        {
            id:Noteid
        })
    $(this).parent().remove();

}
divMouseOver = function(){
    $(this).children("img").show();
    $(this).children("img.update").click(imgClickUpdate)
    $(this).children("img.delete").click(imgClickDelete)

}
divMouseLeave = function(){
    $(this).children("img").hide();
}
sendPositionMessage = function(){
    //TODO 向服务修改笔记位置信息
    div = $(".up_class")
    content = div.children("label").html()
    height = div.height();
    width = div.width();
    posX=div.position().left
    posY=div.position().top
    BGColour=div.css("background-color")
    var contentTextSize= div.css("font-size")
    contentTextSize = contentTextSize.substring(0,contentTextSize.length-2)
    $.post(
        baseUrl+"method=update",
        {
            content :content,
            height :height,
            width :width,
            posX:posX,
            posY:posY,
            BGColour:BGColour,
            contentTextSize:contentTextSize,
            id:div.children("input:hidden").val()
        }
    )
}
subPx = function(str){
    return (str.substring(0,str.length-2))
}
sendAddNewMessage = function(){
    var content = $("#contentInput").val();
    var height = $("#heightInput").val()==""?150:$("#heightInput").val();
    var width = $("#widthInput").val()==""?200:$("#widthInput").val();
    var posX = $("#posX").val()==""?150:$("#posX").val();
    var posY = $("#posY").val()==""?150:$("#posY").val();
    var BGColour = $("#BGColour").val();
    var contentTextSize = $("#contentTextSize").val()==""?30:$("#contentTextSize").val();
    $("body").append("<div class='transparent_class note lastOne'><label></label><img src='img/pencil.png' class='update' hidden><img src='img/delete.png' class='delete' hidden><input hidden noteId=''/></div>")
    $(".lastOne").css({
        'height':height,
        'width':width,
        'top':posY,
        'left':posX,
        'background-color':BGColour,
        'font-size':contentTextSize,
        'position':"absolute"
    })
    if(content==null||content=="")
        $("label:last").html("输入一些东西吧")
    else
        $("label:last").html(content)
    $.post(baseUrl+"method=add",
        {
            content:content,
            height:height,
            width:width,
            posX:posX,
            posY:posY,
            BGColour:BGColour,
            contentTextSize:contentTextSize
        },function(data,status){
            $(".lastOne").children("input:hidden").val(data);
            $(".lastOne").resizable(resizeAction).draggable(resizeAction)
            $(".lastOne").mouseover(divMouseOver).mouseleave(divMouseLeave).mousedown(noteMousedown)
            $(".lastOne").removeClass("lastOne");
        })

}