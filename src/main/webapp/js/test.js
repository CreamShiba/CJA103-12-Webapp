/**
 * 圖片預覽功能
 * @param {HTMLInputElement} input - 觸發事件的檔案輸入框元素
 */
function previewImage(input) {
    // 1. 抓取預覽圖的 img 標籤 (ID 必須與 JSP 中的對應)
    var preview = document.getElementById('blob_holder');
    
    // 2. 檢查是否有選擇檔案，且檔案是否為圖片
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        // 3. 當檔案讀取完成時執行的動作
        reader.onload = function (e) {
            // 將 img 的 src 設定為讀取到的檔案內容 (Base64字串)
            preview.src = e.target.result;
            
            // 確保圖片顯示 (因為如果原本沒圖，可能是 display:none)
            preview.style.display = 'block';
        }

        // 4. 開始讀取檔案
        reader.readAsDataURL(input.files[0]);
    } else {
        // (選用) 如果使用者取消選擇檔案，可以決定是否隱藏圖片或恢復原狀
         preview.style.display = 'none';
    }
}