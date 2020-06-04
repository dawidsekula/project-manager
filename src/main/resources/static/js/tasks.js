/*
*
*   Tasks.js contains scripts for updating and deleting Tasks through Bootstrap's modal
*
*/

$('document').ready(function() {

    // Update Task script
    // When #buttonGetUpdateTaskModal inside of .table is clicked
    $('.table #buttonGetUpdateTaskModal').on('click', function(event) {
        event.preventDefault();
        // HREF address from the <a id="buttonGetUpdateTaskModal"> pointing to Task wanted to update
        var href = $(this).attr('href');
        // Populating form in #updateTaskModal with data from acquired Task's object
        $.get(href, function (task, status) {
            $('#taskCodeToUpdate').val(task.code);
            $('#taskNameUpdate').val(task.name);
            $('#taskDescriptionUpdate').val(task.description);
            $('#taskCategoryUpdate').val(task.category);
            $('#taskStatusUpdate').val(task.taskStatus);
        });
        // Displaying populated modal
        $('#updateTaskModal').modal();

        // Setting behavior of submitting form
        $('#updateTaskForm').on('submit', function (event) {
            event.preventDefault();
            // Getting url from <form id="updateTaskForm"> "action" parameter which is '~/tasks'
            var url = $(this).attr("action");
            // Getting code of edited Task from <input type="hidden" id="taskCodeToUpdate"> in 'modal-footer' section
            var code = $(this).parent().find('#taskCodeToUpdate').val();
            // Combining the two previous
            var postUrl = url + "/" + code;

            // Making Ajax to do a request with given url and updated Task (#updateTaskForm modal's form)
            $.ajax({
                url: postUrl,
                data: $('#updateTaskForm').serialize(),
                type: 'POST',
                // After success redirects to '~/tasks'
                success: function(){
                    window.location.href = url;
                }
                // TODO: Handling errors
            });
        });
    // End of update Task script
    });

    // Delete Task script
    // When #buttonGetDeleteTaskModal inside of .table is clicked
    $('.table #buttonGetDeleteTaskModal').on('click', function(event) {
        event.preventDefault();
        // HREF address from the <a id="buttonGetDeleteTaskModal"> pointing to Task's object wanted to delete
        var href = $(this).attr('href');
        // Setting Task's code in #deleteTaskModal at <input id="taskCodeToDelete">
        $.get(href, function (task, status) {
            $('#taskCodeToDelete').val(task.code);
        });
        // Displaying modal
        $('#deleteTaskModal').modal();

        // Setting behavior of <button id="buttonConfirmTaskDelete">
        $('#buttonConfirmTaskDelete').on('click', function (event) {
            event.preventDefault();
            // Getting url from <div class="modal-footer"> "url" parameter which is '~/tasks'
            var url = $(this).parent().attr("url");
            // Getting Task's code from <input type="hidden" id="taskCodeToDelete"> in 'modal-footer' section
            var code = $(this).parent().find('#taskCodeToDelete').val();
            // Combining the two previous with '/delete' creating endpoint URL for delete request as configured in controller class
            var deleteUrl = url + "/" + code + "/delete";

            // Making Ajax to do a request with given url
            $.ajax({
                url: deleteUrl,
                type: 'GET',
                // After success redirects to '~/tasks'
                success: function(){
                    window.location.href = url;
                }
                // TODO: Handling errors
            });
        });
        // End of delete Task script
    });

});