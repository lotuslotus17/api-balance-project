function updateBalance(id) {
    const balanceInput = document.getElementById(`update-balance-${id}`);
    const balance = parseInt(balanceInput.value);

    if (isNaN(balance)) {
      alert("Please enter a valid number for the balance.");
      return;
    }

    fetch("/balance", {
      method: "POST",
      headers: { "Content-Type": "application/json;charset=UTF-8" },
      body: JSON.stringify({ id: id, amount: balance }),
    })
      .then((response) => response.json())
      .then((data) => {
        const balanceTd = document.getElementById(`balance-${data.id}`);
        balanceTd.textContent = data.balance.balance;
        balanceInput.value = "";
      })
      .catch((error) => {
        alert("Error updating the balance.");
      });
  }