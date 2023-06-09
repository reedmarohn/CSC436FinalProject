Eugenia Morales, Reed Marohn

1) any changes in your goals since the midterm report

    At the point of the midterm report, we still needed to complete lab 4 to use retrofit for our API
    call (which we didn't know about at the time).

Changes made since the midterm report:
    + added the ability to delete an item off a list that updates in real-time.
    + added the ability to select a Date object rather than manual input.
    + added a default image to populate if there is no image returned by the api, or if its manual input
   We changed our mind on:
    - Form Screen drop down to select a category (we didn't implement this because we wanted to populate
      the category from the returned api call)
    - User authentication (we didn't think this was necessary since the data was supposed to be local)

2) the deliverables you have completed and those that you were not able to complete.

  From the tasks we had yet to perform, we were able to complete almost all of them or improve
  existing ones:
    + Barcode API lookup, autofill, and AsyncImage fetching from the URL
    + UI updates: Home summary view (simplified), List View (improved UI, layout)

  The most time consuming deliverables that we successfully implemented:
        - Trying different methods for the barcode to scan from the camera, and then figuring out
        how to actually access the barcode value.
        - We needed to find a free API that worked with most barcodes.
        - The API call returned nested JSON objects, so we spent a lot of time learning how they
        work, how to access the fields appropriately.
        - Since the API call was a coroutine, we were stuck on how to update uiState elements and
        redraw the composable with the updated elements after the API call finished.

  We didn't get to complete:
    - Firebase integration to store our products locally
    - Form field validation (invalid or null fields entered)
    - Error checking in case the API doesn't return a background
    - Alerts/Notifications when things are close to expiring
