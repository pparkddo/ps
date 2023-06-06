from collections import deque


# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Codec:

    def __init__(self):
        self.delimiter: str = ","
        self.empty_node_in_str: str = "#"

    def serialize(self, root):
        """Encodes a tree to a single string.

        :type root: TreeNode
        :rtype: str
        """
        answer: list[str] = []

        def traverse(node: TreeNode):
            if node:
                answer.append(str(node.val))
                traverse(node.left)
                traverse(node.right)
            else:
                answer.append(self.empty_node_in_str)

        traverse(root)

        return self.delimiter.join(answer)

    def deserialize(self, data):
        """Decodes your encoded data to tree.

        :type data: str
        :rtype: TreeNode
        """

        values = iter(data.split(self.delimiter))

        def build_tree() -> TreeNode | None:
            value: str = next(values)
            if value == self.empty_node_in_str:
                return None
            node = TreeNode(int(value))
            node.left = build_tree()
            node.right = build_tree()
            return node

        return build_tree()


# Your Codec object will be instantiated and called as such:
# ser = Codec()
# deser = Codec()
# ans = deser.deserialize(ser.serialize(root))
