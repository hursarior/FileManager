# FileManager
Api para almacenar archivos, de no mas de un mega.

## Endpoint para subir un archivo:

### Método: POST
**Ruta: /api/file-manager/upload**
Descripción: Este endpoint permite subir un archivo al servidor.
El archivo se envía como un parámetro file en la solicitud multipart/form-data. 
El archivo se almacena en el servidor utilizando el servicio Fileservice.
Endpoint para descargar un archivo por su ID:

### Método: GET
**Ruta: /api/file-manager/files/{id}**
Descripción: Este endpoint permite descargar un archivo específico del servidor mediante su ID.
El ID del archivo se pasa como una variable en la URL. 
El servidor buscará el archivo correspondiente en función del ID proporcionado y responderá con el archivo descargable. Además,
se establecen encabezados en la respuesta para especificar el tipo de contenido y el nombre del archivo.
Endpoint para obtener la lista de archivos:

### Método: GET
**Ruta: /api/file-manager/files**
Descripción: Este endpoint devuelve una lista de todos los archivos almacenados en el servidor. 
Los archivos se devuelven en formato JSON como una lista de objetos ResponseFile, 
que presumiblemente contiene información sobre los archivos almacenados.
