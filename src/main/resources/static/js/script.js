(function($) {

  "use strict";

  var initPreloader = function() {
    $(document).ready(function($) {
    var Body = $('body');
        Body.addClass('preloader-site');
    });
    $(window).load(function() {
        $('.preloader-wrapper').fadeOut();
        $('body').removeClass('preloader-site');
    });
  }

  // init Chocolat light box
	var initChocolat = function() {
		Chocolat(document.querySelectorAll('.image-link'), {
		  imageSize: 'contain',
		  loop: true,
		})
	}

  var initSwiper = function() {

    var swiper = new Swiper(".main-swiper", {
      speed: 500,
      pagination: {
        el: ".swiper-pagination",
        clickable: true,
      },
    });

    var category_swiper = new Swiper(".category-carousel", {
      slidesPerView: 6,
      spaceBetween: 30,
      speed: 500,
      navigation: {
        nextEl: ".category-carousel-next",
        prevEl: ".category-carousel-prev",
      },
      breakpoints: {
        0: {
          slidesPerView: 2,
        },
        768: {
          slidesPerView: 3,
        },
        991: {
          slidesPerView: 4,
        },
        1500: {
          slidesPerView: 6,
        },
      }
    });

    var brand_swiper = new Swiper(".brand-carousel", {
      slidesPerView: 4,
      spaceBetween: 30,
      speed: 500,
      navigation: {
        nextEl: ".brand-carousel-next",
        prevEl: ".brand-carousel-prev",
      },
      breakpoints: {
        0: {
          slidesPerView: 2,
        },
        768: {
          slidesPerView: 2,
        },
        991: {
          slidesPerView: 3,
        },
        1500: {
          slidesPerView: 4,
        },
      }
    });

    var products_swiper = new Swiper(".products-carousel", {
      slidesPerView: 5,
      spaceBetween: 30,
      speed: 500,
      navigation: {
        nextEl: ".products-carousel-next",
        prevEl: ".products-carousel-prev",
      },
      breakpoints: {
        0: {
          slidesPerView: 1,
        },
        768: {
          slidesPerView: 3,
        },
        991: {
          slidesPerView: 4,
        },
        1500: {
          slidesPerView: 6,
        },
      }
    });
  }

  var initProductQty = function(){

    $('.product-qty').each(function(){

      var $el_product = $(this);
      var quantity = 0;

      $el_product.find('.quantity-right-plus').click(function(e){
          e.preventDefault();
          var quantity = parseInt($el_product.find('#quantity').val());
          $el_product.find('#quantity').val(quantity + 1);
      });

      $el_product.find('.quantity-left-minus').click(function(e){
          e.preventDefault();
          var quantity = parseInt($el_product.find('#quantity').val());
          if(quantity>0){
            $el_product.find('#quantity').val(quantity - 1);
          }
      });

    });

  }

  // init jarallax parallax
  var initJarallax = function() {
    jarallax(document.querySelectorAll(".jarallax"));

    jarallax(document.querySelectorAll(".jarallax-keep-img"), {
      keepImg: true,
    });
  }

  // document ready
  $(document).ready(function() {
    
    initPreloader();
    initSwiper();
    initProductQty();
    initJarallax();
    initChocolat();

    $("#profile").click(function(){
            $.get("/api/profile", function(data, status){
            console.log(data);
                $('#name').text(data.name);
                $('#username').text(data.username);
                $('#mobileNumber').text(data.mobileNumber);
                $('#creditCardNumber').text(data.creditCardNumber);
                $('#myModal').modal('show')
            });
    });

    $("#saveProfileButton").click(function(){
                let name = $('#name').val();
                let username = $('#username').val();
                let mobileNumber = $('#mobileNumber').val();
                let creditCardNumber = $('#creditCardNumber').val();

                var token = $("meta[name='_csrf']").attr("content");
                var headerValue = $("meta[name='_csrf_header']").attr("content");
                let payload = {
                    "name": name,
                    "username": username,
                    "mobileNumber": mobileNumber,
                    "creditCardNumber":creditCardNumber,
                }
                console.log(token);
                $.ajaxSetup({
                   headers:{
                    'X-CSRF-TOKEN': token
                   }
                });
                $.ajax({
                  type: "POST",
                  url: "/api/profile",
                  data: payload,
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

    $("#closeModal").click(function(){
        $('#myModal').modal('hide')
    });

  }); // End of a document

})(jQuery);