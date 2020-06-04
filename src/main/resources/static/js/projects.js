/*
*
*   Projects.js contains scripts for updating and deleting Projects through Bootstrap's modal
*
*/

$('document').ready(function() {

    // Update Project script
    // When #buttonGetUpdateProjectModal inside of .table is clicked
    $('.table #buttonGetUpdateProjectModal').on('click', function(event) {
        event.preventDefault();
        // HREF address from the <a id="buttonGetUpdateProjectModal"> pointing to Project wanted to update
        var href = $(this).attr('href');
        // Populating form in #updateProjectForm with data from acquired Project's object
        $.get(href, function (project, status) {
            $('#projectUuidToUpdate').val(project.uuid);
            $('#projectUuid').val(project.uuid);
            $('#projectTagUpdate').val(project.projectTag);
            $('#projectNameUpdate').val(project.name);
            $('#projectDescriptionUpdate').val(project.description);
            $('#projectStatusUpdate').val(project.projectStatus);
        });
        // Displaying populated modal
        $('#updateProjectModal').modal();

        // Setting behavior of submitting form
        $('#updateProjectForm').on('submit', function (event) {
            event.preventDefault();
            // Getting url from <form id="updateProjectForm"> "action" parameter which is '~/projects'
            var url = $(this).attr("action");
            // Getting UUID of edited Project from <input type="hidden" id="projectUuidToUpdate"> in 'modal-footer' section
            var uuid = $(this).parent().find('#projectUuidToUpdate').val();
            // Combining the two previous
            var postUrl = url + "/" + uuid;

            // Making Ajax to do a request with given url and updated Project (#updateProjectForm modal's form)
            $.ajax({
                url: postUrl,
                data: $('#updateProjectForm').serialize(),
                type: 'POST',
                // After success redirects to '~/projects'
                success: function(){
                    window.location.href = url;
                }
                // TODO: Handling errors
            });
        });
        // End of update Project script
    });

    // Delete Project script
    // When #buttonGetDeleteProjectModal inside of .table is clicked
    $('.table #buttonGetDeleteProjectModal').on('click', function(event) {
        event.preventDefault();
        // HREF address from the <a id="buttonGetDeleteProjectModal"> pointing to Project wanted to delete
        var href = $(this).attr('href');
        // Setting UUID in #deleteProjectModal at <input id="projectUuidToDelete">
        $.get(href, function (project, status) {
            $('#projectUuidToDelete').val(project.uuid);
        });
        // Displaying modal
        $('#deleteProjectModal').modal();

        // Setting behavior of <button id="buttonConfirmProjectDelete">
        $('#buttonConfirmProjectDelete').on('click', function (event) {
            event.preventDefault();
            // Getting url from <div class="modal-footer"> "url" parameter which is '~/projects'
            var url = $(this).parent().attr("url");
            // Getting Project's UUID from <input type="hidden" id="projectUuidToDelete"> in 'modal-footer' section
            var code = $(this).parent().find('#projectUuidToDelete').val();
            // Combining the two previous with '/delete' creating endpoint URL for delete request as configured in controller class
            var deleteUrl = url + "/" + code + "/delete";

            // Making Ajax to do a request with given url
            $.ajax({
                url: deleteUrl,
                type: 'GET',
                // After success redirects to '~/projects'
                success: function(){
                    window.location.href = url;
                }
                // TODO: Handling errors
            });
        });
        // End of delete Project script
    });

});