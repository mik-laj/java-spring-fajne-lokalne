(function ($) {
    var csrf_value = $('meta[name="csrf-value"]').attr("value");
    $("[data-action=vote]").click(function (ev) {
        ev.preventDefault();
        var $this = $(this);
        var id = $(this).closest("[data-id]").attr("data-id");
        var value = $(this).attr("data-action-value");

        $.post("/api/vote/product/" + id + "/", {value: value, _csrf: csrf_value}, function(data) {
            var vote = data.vote_value / data.vote_count;
            // debugger;
            // $this.closest("[data-id]")
            //     .find("[data-value]")
            //     .attr("[data-value]", vote.toFixed(2).toString());
            $this.closest("[data-value-int  ]").attr("[data-value-int]", vote | 0);
        });
    });
})(jQuery);