# Differ 

This application has the purpose of receiving and storing binary data to check for differences between them.
It has 3 main endpoints

  1 - http://localhost:8080/v1/diff/{id}/left
  
  2 - http://localhost:8080/v1/diff/{id}/right
  
  3 - http://localhost:8080/v1/diff/{id}

# Important
  The binary data must be already encoded and sent via JSON inside the "data" field
  
  {
	"data": "binary data"
  }
  
----------------------------------------
This endpoint can receive both files at the same time and verify the differences without storing them
  
  4 - http://localhost:8080/v2/diff
  
  It uses a slightly different data structure:
  
  {
	"data1": "binary data1", 
  "data2": "binary data2"
  }
