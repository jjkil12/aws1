var book_main = {
    init : function () {
        var _this = this;
        $('#btn-book-save').on('click', function () {
            _this.save();
        });

        $('#btn-book-update').on('click', function () {
            _this.update();
        });

        $('#btn-book-delete').on('click', function () {
            _this.delete();
        });

        $('#btn-reply-save').on('click', function () {
            _this.reply_save();
        });

        $('#btn-reply-delete').on('click', function () {
            _this.reply_delete();
        });

    },
    reply_update : function (book_id,reply_id,reply_text) {
    var data = {
                update_text: $('#'+reply_text).val(),
                user_mail: $('#reply_user_mail').val()
            };


            $.ajax({
                type: 'PUT',
                url: '/api/v1/books/'+book_id+'/reply/'+reply_id,
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                alert('댓글이 수정되었습니다.');
                window.location.href = '/book_detail/'+book_id;
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
    },
    reply_delete : function (book_id,reply_id) {
            $.ajax({
                type: 'DELETE',
                url: '/api/v1/books/'+book_id+'/reply/'+reply_id,
                dataType: 'json',
                contentType:'application/json; charset=utf-8'
            }).done(function() {
                alert('댓글이 삭제되었습니다.');
                window.location.href = '/book_detail/'+book_id;
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
    },
    reply_save : function () {
            var data = {
                text: $('#reply_text').val(),
                user_mail : $('#user_mail').val()
            };

            var book_id = $('#book_id').val();

            $.ajax({
                type: 'POST',
                url: '/api/v1/'+book_id+'/reply',
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                alert('댓글이 등록 완료 되었습니다.');
                window.location.href = '/book_detail/'+book_id;
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
    },
    save : function () {
        var data = {
            genre: $('#genre').val(),
            title: $('#title').val(),
            comment: $('#comment').val(),
            user_email: $('#user').val(),
            book_name: $('#book_name').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/books',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/book_list';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            genre: $('#genre').val(),
            title: $('#title').val(),
            book_name: $('#book_name').val(),
            comment: $('#comment').val()
        };

        var book_id = $('#book_id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/books/'+book_id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/book_list';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var book_id = $('#book_id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/books/'+book_id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/book_list';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};
book_main.init();