$(document).ready(function(){
    $('.edit-book').on('click', function(){
        $data = $(this).data('book');
        $id = $data.split(',')[0];
        $title = $data.split(',')[1];
        $description = $data.split(',')[2];
        
        $('#edit-book-id').val($id);
        $('#edit-book-title').val($title);
        $('#edit-book-description').val($description);
    });
    
    $('.view-book').on('click', function(){        
        $data = $(this).data('book');
        $id = $data.split(',')[0];
        $title = $data.split(',')[1];
        $description = $data.split(',')[2];
        
        $('#view-book-id').val($id);
        $('#view-book-title').val($title);
        $('#view-book-description').val($description);
    });
});