(ns show-grid.core)
(import '(javax.swing JFrame JLabel JPanel JTable JEditorPane JScrollPane))
(import '(javax.swing.table DefaultTableModel))

(defn row? [data] 
	(or (seq? data) (vector? data) (map? data)))

(defn tag [data]
	(if (row? data) 
		(str "<tr>" (apply str (map #(tag %) data)) "</tr>")
		(str "<td>" data "</td>")))

(defn to-html [data] 
	(let [result (str "<table border='1' width='100%'>" (tag data) "</table>")]
		result))
			
(defn show-grid 
	([data width height] 
		(let [
			editor-pane (JEditorPane. "text/html" (to-html data))
			scroll-pane (JScrollPane. editor-pane)
			frame (JFrame. "show-grid") ]
				(doto frame
					(.setSize width height)
					(.setContentPane scroll-pane)
					(.setVisible true)))
					nil)
	([data]
		(show-grid data 250 400)))