package org.example.frame;

import org.example.game.GameBoard;
import org.example.game.Stick;
import org.example.game.Node;
import org.example.game.Stone;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class DrawingPanel extends JPanel
{
    private final MainFrame frame;
    private GameBoard gameBoard;

    private BufferedImage gameBoardImage;
    private Node lastSelectedNode;
    private boolean isRedTurn;

    int rows = 1, cols = 1;

    public boolean getIsRedTurn()
    {
        return isRedTurn;
    }

    public void setIsRedTurn(boolean redTurn)
    {
        isRedTurn = redTurn;
    }

    public Node getLastSelectedNode()
    {
        return lastSelectedNode;
    }

    public void setLastSelectedNode(Node lastSelectedNode)
    {
        this.lastSelectedNode = lastSelectedNode;
    }

    public DrawingPanel(MainFrame frame)
    {
        this.frame = frame;
        gameBoardImage = null;
        lastSelectedNode = null;
        isRedTurn = true;

        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {

                gameBoard = getGameBoard();
                double cellWidth = (double) getWidth() / gameBoard.getNodes()[0].length;
                double cellHeight = (double) getHeight() / gameBoard.getNodes().length;

                int row = (int) (e.getY() / cellHeight);
                int col = (int) (e.getX() / cellWidth);

                int circleDiameter = (int) Math.min(cellWidth, cellHeight) / 2;
                int circleRadius = circleDiameter / 2;

                double xInCell = e.getX() % cellWidth;
                double yInCell = e.getY() % cellHeight;

                // Check if the click is within the circle
                double distanceToCenter = Math.hypot(xInCell - cellWidth / 2, yInCell - cellHeight / 2);
                if (distanceToCenter > circleRadius)
                {
                    return;
                }

                Node clickedNode = gameBoard.getNodes()[row][col];

                // Validate the move
                if (clickedNode != null && !clickedNode.hasStone() &&
                        (lastSelectedNode == null || lastSelectedNode.getAdjacentNodes().contains(clickedNode)))
                {

                    clickedNode.setStone(new Stone(clickedNode, isRedTurn ? Color.RED : Color.BLUE));
                    System.out.println(clickedNode);

                    System.out.println("Placed a stone on node (" + row + ", " + col + ") of color " + (isRedTurn ? "Red" : "Blue"));

                    // Update the game state
                    lastSelectedNode = clickedNode;
                    isRedTurn = !isRedTurn;

                    System.out.println("Turn changed. It's now " + (isRedTurn ? "Red's" : "Blue's") + " turn.");

                    repaint();

                    // Check if the game is over
                    if (lastSelectedNode.getAdjacentNodes().stream().allMatch(Node::hasStone))
                    {
                        JOptionPane.showMessageDialog(null, (isRedTurn ? "Blue" : "Red") + " player wins!");
                    }
                }
                else
                {
                    System.out.println("Invalid move. Node (" + row + ", " + col + ") is either occupied or not adjacent to the last selected node.");
                }
            }
        });
    }

    final void init(int rows, int cols)
    {
        if (rows > 0 && cols > 0)
        {
            this.rows = rows;
            this.cols = cols;
        }
        repaint();
    }

    public void setGameBoard(GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;
    }

    public void resetGameState()
    {
        lastSelectedNode = null;
        isRedTurn = true;
    }


    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        gameBoardImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2Image = gameBoardImage.createGraphics();

        int width = getWidth();
        int height = getHeight();

        if (rows > 1 && cols > 1)
        {
            double cellWidth = (double) width / cols;
            double cellHeight = (double) height / rows;

            int circleDiameter = (int) Math.min(cellWidth, cellHeight);
            circleDiameter *= 0.5;

            int xOffset = (int) cellWidth / 2;
            int yOffset = (int) cellHeight / 2;

            g2Image.setColor(Color.BLACK);
            g2Image.setStroke(new BasicStroke(1));


            for (int i = 0; i < cols; i++)
            {
                for (int j = 0; j < rows; j++)
                {
                    int x = (int) (i * cellWidth) + xOffset;
                    int y = (int) (j * cellHeight) + yOffset;
                    if (i < cols)
                    {
                        g2Image.drawLine(x, yOffset, x, (int) ((rows - 1) * cellHeight) + yOffset);
                    }
                    if (j < rows)
                    {
                        g2Image.drawLine(xOffset, y, (int) ((cols - 1) * cellWidth) + xOffset, y);
                    }
                    g2Image.drawOval(x - circleDiameter / 2, y - circleDiameter / 2, circleDiameter, circleDiameter);
                }
            }

            g2Image.setStroke(new BasicStroke(3));


            for (Node[] nodeRow : gameBoard.getNodes())
            {
                for (Node node : nodeRow)
                {
                    int startX = (int) (node.getCol() * cellWidth) + xOffset;
                    int startY = (int) (node.getRow() * cellHeight) + yOffset;

                    for (Node adjacentNode : node.getAdjacentNodes())
                    {
                        int endX = (int) (adjacentNode.getCol() * cellWidth) + xOffset;
                        int endY = (int) (adjacentNode.getRow() * cellHeight) + yOffset;
                        g2Image.drawLine(startX, startY, endX, endY);
                    }
                }
            }


            for (int row = 0; row < rows; row++)
            {
                for (int col = 0; col < cols; col++)
                {
                    Node node = gameBoard.getNodes()[row][col];


                    if (node.hasStone())
                    {
                        int x = (int) (node.getCol() * cellWidth) + xOffset;
                        int y = (int) (node.getRow() * cellHeight) + yOffset;

                        Color stoneColor = node.getStone().getColor();

                        g2Image.setColor(stoneColor);
                        g2Image.fillOval(x - circleDiameter / 2, y - circleDiameter / 2, circleDiameter, circleDiameter);
                    }
                }
            }
        }
        g.drawImage(gameBoardImage, 0, 0, this);
    }

    public void exportGameBoardImage(String filename)
    {
        File outputFile = new File(filename);
        try
        {
            ImageIO.write(gameBoardImage, "png", outputFile);
            System.out.println("Game board image exported to " + filename);
        } catch (IOException e)
        {
            System.out.println("Failed to export game board image: " + e.getMessage());
        }
    }

    public GameBoard getGameBoard()
    {
        return gameBoard;
    }

}
