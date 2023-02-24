function transferMoney() {
  document.getElementById("transfer-form").addEventListener("submit", function(event) {
    event.preventDefault();

    var senderId = document.getElementById("sender-id").value;
    var recipientId = document.getElementById("recipient-id").value;
    var amount = document.getElementById("amount").value;

    // Make API call to transfer money between users
    fetch("/transfer", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        userFromId: senderId,
        userToId: recipientId,
        amount: amount
      })
    })
    .then(function(response) {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .then(function(data) {
      // Update the sender and recipient balance on the page
      const senderBalance = document.getElementById(`balance-${data.userFromId}`);
      const recipientBalance = document.getElementById(`balance-${data.userToId}`);
      senderBalance.textContent = data.userFromBalance;
      recipientBalance.textContent = data.userToBalance;

      // Reset the form
      document.getElementById("sender-id").value = "";
      document.getElementById("recipient-id").value = "";
      document.getElementById("amount").value = "";
    })
    .catch(function(error) {
      console.error("There was a problem with the fetch operation:", error);
    });
  });
}
