const upload_preset = "dil-se-rasoi";
const cloud_name = "dxqazbv8d";
const api_url = `https://api.cloudinary.com/v1_1/${cloud_name}/image/upload`;

export const UploadImageToCloudinary = async (file) => {
  const data = new FormData();
  data.append("file", file);
  data.append("upload_preset", upload_preset);
  data.append("cloud_name", cloud_name);

  const response = await fetch(api_url, {
    method: "post",
    body: data,
  });

  const fileData = await response.json();
  return fileData.url;
};
