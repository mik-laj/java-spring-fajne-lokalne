(function($){
   $('[data-colleciton-form]').each(function(i, form){

       function replaceall(target, search, replacement) {
           return target.split(search).join(replacement);
       };

       var $form = $(form);
       var $tbody = $form.find('tbody');
       var $rows = $tbody.children().filter('tr');
       var max_id = Math.max.apply(null, Array.from($rows).map(d => d.getAttribute('data-row-index'))) | 0;
       var template_html = $form.find('template')[0].innerHTML;
       debugger;
       $form.on('click', '[data-action=remove-row]', function(ev) {
            var $this = this;
            $this.closest('tr').remove();
            ev.preventDefault();
            return true;
       });
       $form.find('[data-action=add-row]').on('click', function(ev){
           max_id++;
           var $row = $(replaceall(template_html, "PREVIEW", max_id));
           $tbody.append($row);
           if(jQuery.fn.select2){
               $row.find('.select2').select2();
           }

           ev.preventDefault();
           return true;
       });
       if(jQuery.fn.select2) {
           $('.select2').select2();
       }
   })
})(jQuery);