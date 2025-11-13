#  Edge Removal from a Minimum Spanning Tree (MST)

student: Nurbek Shagarbek
predmet: DAA

##  Goal
This project demonstrates how to:
1. Build a **Minimum Spanning Tree (MST)** from a given weighted undirected graph.
2. Display the MST edges before any modification.
3. **Remove one edge** from the MST.
4. Display the resulting **disconnected components**.
5. Find and display the **replacement edge** that reconnects the graph into a new MST.

The implementation uses **Kruskal’s algorithm** and a **Union-Find (Disjoint Set)** structure for efficiency.

---

##  Features

- Builds an MST from input edges.
- Displays all MST edges and total weight.
- Removes one MST edge.
- Identifies and displays the resulting components.
- Finds the optimal replacement edge to reconnect the components.
- Displays the **new MST** after reconnection.

---

| File | Description |
|------|--------------|
| **Edge.java** | Represents a weighted undirected edge. |
| **Graph.java** | Defines the graph structure and stores all edges. |
| **KruskalMST.java** | Builds the MST using Kruskal’s algorithm and Union-Find. |
| **Main.java** | Main execution file: build → remove → split → reconnect. |

---

## How to Run

###  Clone this repository
```bash
git clone https://github.com/yourusername/MSTEdgeRemoval.git
cd MSTEdgeRemoval
