;(function() {
    $().ready(function () {
        $("#send").click(function () {
            var scriptBody = $("#script-body").val();
            $.post("/script/execute", {script: scriptBody})
                .done(function (data) {
                    $("#response").text(data);
                });
        });
    });
}());