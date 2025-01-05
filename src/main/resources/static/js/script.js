$(document).ready(function() {

  $('.color-choose input').on('click', function() {
      var headphonesColor = $(this).attr('data-image');

      $('.active').removeClass('active');
      $('.left-column img[data-image = ' + headphonesColor + ']').addClass('active');
      $(this).addClass('active');
  });

  $('#ratings span').on('click', function() {
        $('#ratings span').removeClass('checked');
        let id = $(this).attr('id');
        $(this).prevAll('#ratings span').addClass('checked');
        $(this).addClass('checked');
        $.ajax({
                            type: "POST",
                            url: "/api/rating",
                            data: {
                                rating : id
                            },
                            success: function( data ) {
                              if(data == 'success') {
                                 $('#message').css('color', 'green');
                              }else {
                                 $('#message').css('color', 'red');
                              }
                              $('#message').text(data);
                            },
                            dataType: "text"
                          });
  });


});
